CREATE TABLE IF NOT EXISTS shop (
    shop_id VARCHAR(255) NOT NULL PRIMARY KEY,
    shop_name VARCHAR(255) NOT NULL,
    shop_url VARCHAR(255) NOT NULL,
    date_of_registration DATE NOT NULL,
    lat FLOAT(10, 6) NOT NULL,
    lng FLOAT(10, 6) NOT NULL,
    is_bird TINYINT(1) NOT NULL,
    is_insurance TINYINT(1) NOT NULL,
    is_brand TINYINT(1) NOT NULL,
    need_tip TINYINT(1) NOT NULL,
    promotion JSON
);

INSERT INTO shop
(shop_id,shop_name,shop_url,date_of_registration,lat,lng,is_bird,is_insurance,is_brand,need_tip,promotion)
VALUES
('00000000-0000-0000-0000-000000000000','Test 1','http://localhost:4000/apis/img/tarjan.jpg','2018/08/15',13.0412658,80.2338514,1,1,1,1,'[{"variety":"SUBTRACTION","slogan":"Deduct $20.0"}, {"variety":"SPECIAL","slogan":"$16.0 Off"}, {"variety":"DISCOUNT","slogan":"50% Off"}, {"variety":"NEW","slogan":"New User Deduct $17.0"}]'),
('00000000-0000-0000-0000-000000000001','Test 2','http://localhost:4000/apis/img/tarjan.jpg','2018/07/15',13.0412658,80.2338514,1,1,1,1,'[{"variety":"SUBTRACTION","slogan":"Deduct $20.0"}, {"variety":"SPECIAL","slogan":"$16.0 Off"}, {"variety":"DISCOUNT","slogan":"50% Off"}, {"variety":"NEW","slogan":"New User Deduct $17.0"}]'),
('00000000-0000-0000-0000-000000000002','Test 3','http://localhost:4000/apis/img/tarjan.jpg','2018/08/11',13.0412658,80.2338514,1,1,1,1,'[{"variety":"SUBTRACTION","slogan":"Deduct $20.0"}, {"variety":"SPECIAL","slogan":"$16.0 Off"}, {"variety":"DISCOUNT","slogan":"50% Off"}, {"variety":"NEW","slogan":"New User Deduct $17.0"}]'),
('00000000-0000-0000-0000-000000000003','Test 4','http://localhost:4000/apis/img/tarjan.jpg','2018/01/13',13.0412658,80.2338514,1,1,1,1,'[{"variety":"SUBTRACTION","slogan":"Deduct $20.0"}, {"variety":"SPECIAL","slogan":"$16.0 Off"}, {"variety":"DISCOUNT","slogan":"50% Off"}, {"variety":"NEW","slogan":"New User Deduct $17.0"}]'),
('00000000-0000-0000-0000-000000000004','Test 5','http://localhost:4000/apis/img/tarjan.jpg','2018/06/30',13.0412658,80.2338514,1,1,1,1,'[{"variety":"SUBTRACTION","slogan":"Deduct $20.0"}, {"variety":"SPECIAL","slogan":"$16.0 Off"}, {"variety":"DISCOUNT","slogan":"50% Off"}, {"variety":"NEW","slogan":"New User Deduct $17.0"}]');