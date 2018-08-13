CREATE TABLE IF NOT EXISTS shop (
    shop_id VARCHAR(255) NOT NULL PRIMARY KEY,
    shopname VARCHAR(255) NOT NULL,
    date_of_registeration DATE NOT NULL,
    lat FLOAT(10, 6) NOT NULL,
    lng FLOAT(10, 6) NOT NULL,
    is_bird TINYINT(1) NOT NULL,
    is_insurance TINYINT(1) NOT NULL,
    is_brand TINYINT(1) NOT NULL,
    need_tip TINYINT(1) NOT NULL,
    promotion JSON
);
