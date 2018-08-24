CREATE TABLE IF NOT EXISTS shop (
    shop_id VARCHAR(255) NOT NULL PRIMARY KEY,
    shop_name VARCHAR(255) NOT NULL,
    shop_address VARCHAR(255) NOT NULL,
    shop_url TEXT NOT NULL,
    shop_service VARCHAR(255) NOT NULL,
    shop_annoucement VARCHAR(255) NOT NULL,
    shop_slogan VARCHAR(255) NOT NULL,
    shop_delivery_fee FLOAT NOT NULL,
    shop_send_threshold FLOAT NOT NULL,
    date_of_registration DATE NOT NULL,
    lat FLOAT(10, 6) NOT NULL,
    lng FLOAT(10, 6) NOT NULL,
    is_bird TINYINT(1) NOT NULL,
    is_insurance TINYINT(1) NOT NULL,
    is_brand TINYINT(1) NOT NULL,
    need_tip TINYINT(1) NOT NULL,
    activity_num INT NOT NULL,
    promotion JSON
);

INSERT INTO shop
(shop_id,shop_name,shop_address, shop_url,shop_service,shop_annoucement,shop_slogan,shop_delivery_fee,shop_send_threshold,date_of_registration,lat,lng,is_bird,is_insurance,is_brand,need_tip,activity_num,promotion)
VALUES
('00000000-0000-0000-0000-000000000000','港岛记(北京知春路店)', '北京市海淀区中关村大街15 - 9号F1-R133、R137、R138', 'https://fuss10.elemecdn.com/d/ab/865960541f74551858f8ed9d3685bjpeg.jpeg?imageMogr/format/webp/thumbnail/!130x130r/gravity/Center/crop/130x130/','蜂鸟专送／34分钟送达／配送费¥5', '公告：新店开张！感谢惠顾！如有特殊需求，请备注或打电话告诉我们，谢谢大家', '新店开张！感谢惠顾！如有特殊需求，请备注或打电话告诉我们，谢谢大家', 5, 20, '2018/08/15',13.0412658,80.2338514,1,1,1,1,10,'[{"variety":"SUBTRACTION","slogan":"Deduct $20.0"}, {"variety":"SPECIAL","slogan":"$16.0 Off"}, {"variety":"DISCOUNT","slogan":"50% Off"}, {"variety":"NEW","slogan":"New User Deduct $17.0"}]');
