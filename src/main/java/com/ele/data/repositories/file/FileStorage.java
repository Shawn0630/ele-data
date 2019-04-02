package com.ele.data.repositories.file;

import com.ele.data.repositories.MockStorage;

public class FileStorage implements MockStorage {
    @Override
    public ResultRepository resultRepository() {
        return new FileResultRepository();
    }
}
