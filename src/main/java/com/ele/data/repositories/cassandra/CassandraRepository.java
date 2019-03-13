package com.ele.data.repositories.cassandra;

import akka.Done;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import javax.annotation.Nullable;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class CassandraRepository {
    protected final Session session;
    protected final int parallelism;
    private final String[] tableNames;

    CassandraRepository(final Session session, final int parallelism, final String... tableNames) {
        this.session = session;
        this.parallelism = parallelism;
        this.tableNames = tableNames;
    }

    static String convertFieldsToNames(String fields) {
        return fields.substring(0, fields.length() - 1)
                .replaceAll("<[^>]+>", "")
                .replaceAll("[^,]+", "");
    }

    protected CompletionStage<Done> executeAsyncDone(Statement statement) {
        CompletableFuture<Done> future = new CompletableFuture<>();
        Futures.addCallback(session.executeAsync(statement), new FutureCallback<ResultSet>() {
            @Override
            public void onSuccess(@Nullable ResultSet result) {
                future.complete(Done.getInstance());
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);

            }
        }, ForkJoinPool.commonPool());

        return future;
    }

    protected CompletionStage<ResultSet> executeAsync(Statement statement) {
        return executeAsync(statement, ForkJoinPool.commonPool());
    }

    protected CompletionStage<ResultSet> executeAsync(Statement statement, Executor executor) {
        CompletableFuture<ResultSet> future = new CompletableFuture<>();
        Futures.addCallback(session.executeAsync(statement), new FutureCallback<ResultSet>() {
            @Override
            public void onSuccess(@Nullable ResultSet result) {
                future.complete(result);
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        }, executor);

        return future;
    }

    protected <T> CompletableFuture<T> selectSingleRow(Statement statement, Function<Row, T> decode) {
        return selectSingleRow(statement, decode, ForkJoinPool.commonPool());
    }

    protected <T> CompletableFuture<T> selectSingleRow(Statement statement, Function<Row, T> decode, Executor executor) {
        final CompletableFuture<T> future = new CompletableFuture<>();
        Futures.addCallback(session.executeAsync(statement), new FutureCallback<ResultSet>() {
            @Override
            public void onSuccess(@Nullable ResultSet result) {
                final Row row = result.one();
                if (row == null) {
                    future.completeExceptionally(new NoSuchElementException());
                } else {
                    try {
                        future.complete(decode.apply(row));
                    } catch (Throwable e) {
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        }, executor);

        return future;
    }

    protected CompletableFuture<Optional<Row>> selectSingOptionalRow(Statement statement) {
        final CompletableFuture<Optional<Row>> future = new CompletableFuture<>();
        Futures.addCallback(session.executeAsync(statement), new FutureCallback<ResultSet>() {
            @Override
            public void onSuccess(@Nullable ResultSet result) {
                final Row row = result.one();
                if (row == null) {
                    future.complete(Optional.empty());
                } else {
                    try {
                        future.complete(Optional.of(row));
                    } catch (Throwable e) {
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        }, ForkJoinPool.commonPool());

        return future;
    }

    protected <T> CompletableFuture<Optional<T>> selectSingOptionalRow(Statement statement, Function<Row, T> decode) {
        final CompletableFuture<Optional<T>> future = new CompletableFuture<>();
        Futures.addCallback(session.executeAsync(statement), new FutureCallback<ResultSet>() {
            @Override
            public void onSuccess(@Nullable ResultSet result) {
                final Row row = result.one();
                if (row == null) {
                    future.complete(Optional.empty());
                } else {
                    try {
                        future.complete(Optional.of(decode.apply(row)));
                    } catch (Throwable e) {
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                future.completeExceptionally(t);
            }
        }, ForkJoinPool.commonPool());

        return future;
    }

}
