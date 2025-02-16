INSERT INTO product VALUES (1, '算法导论', 128, 9.5, '<p>影响全球千万程序员的“算法圣经”！国内外千余所高校采用！MIT四大名师联手铸就</p>','/static/cover/ita.jpg','/static/desc/ita.jpg');
INSERT INTO product VALUES (2, '人工智能导论', 49, 9.1, '<p>面向非计算机专业的人工智能入门书籍，新一代信息技术丛书。</p>','/static/cover/ai.jpg','/static/desc/ai.jpg');

INSERT INTO specification VALUES (1, '作者','科尔曼',1);
INSERT INTO specification VALUES (2, '副标题','算法',1);
INSERT INTO specification VALUES (3, 'ISBN','9787111407010',1);
INSERT INTO specification VALUES (4, '书名','算法导论',1);
INSERT INTO specification VALUES (5, '丛书','计算机科学丛书',1);
INSERT INTO specification VALUES (6, '页数','432',1);
INSERT INTO specification VALUES (7, '出版社','机械工业出版社',1);
INSERT INTO specification VALUES (8, '出版年','2013-2-25',1);
INSERT INTO specification VALUES (9, '装帧','平装',1);
INSERT INTO specification VALUES (10, '作者','李德毅',2);
INSERT INTO specification VALUES (11, 'ISBN','9787504681195',2);
INSERT INTO specification VALUES (12, '书名','人工智能导论',2);
INSERT INTO specification VALUES (13, '副标题','人工智能导论',2);
INSERT INTO specification VALUES (14, '页数','413',2);
INSERT INTO specification VALUES (15, '出版社','中国科学技术出版社',2);
INSERT INTO specification VALUES (16, '出版年','2018-8',2);
INSERT INTO specification VALUES (17, '装帧','平装',2);


INSERT INTO advertisement VALUES (1, '/static/carousel/ai.png',2);
INSERT INTO advertisement VALUES (2, '/static/carousel/ai.png',2);
INSERT INTO advertisement VALUES (3, '/static/carousel/ai.png',2);

INSERT INTO stockpile VALUES (1, 30, 0, 1);
INSERT INTO stockpile VALUES (2, 30, 0, 2);

INSERT INTO account VALUES (1, 'tao', '$2a$10$iIim4LtpT2yjxU2YVNDuO.yb1Z2lq86vYBZleAeuIh2aFXjyoMCM.' , '陶然', '', '18888886666', 'taosoft@gmail.com', 'xxxx有限公司');
INSERT INTO wallet VALUES (1, 300, 1);
