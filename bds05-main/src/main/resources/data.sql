INSERT INTO tb_user (name, email, password) VALUES ('Bob Brown', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, password) VALUES ('Ana maria', 'ana@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');


INSERT INTO tb_role (authority) VALUES ('ROLE_VISITOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_MEMBER');


INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);


INSERT INTO tb_genres (name) VALUES ('AÇÃO');
INSERT INTO tb_genres (name) VALUES ('AVENTURA');
INSERT INTO tb_genres (name) VALUES ('COMEDIA');
INSERT INTO tb_genres (name) VALUES ('TERROR');
INSERT INTO tb_genres (name) VALUES ('FUTURISTA');

INSERT INTO tb_movies (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Senhor dos aneis', 'Uma aventura medieval',1999,'https://cdn.pixabay.com/photo/2020/05/31/09/40/online-course-5242018_1280.jpg','é uma trilogia cinematográfica dirigida por Peter Jackson com base na obra-prima homónima de J. R. R. Tolkien. Os três filmes foram rodados em simultâneo na Nova Zelândia,[1] faturaram cerca de 3 bilhões (US$ 2.925.155.189) de dólares de receitas conjuntas de bilheteira[2] e foram galardoados com 17 Oscars, entre os 30 para os quais foram nomeados.[3] e é a franquia cinematográfica mais premiada da história ',1);
INSERT INTO tb_movies (title, sub_title, year, img_url, synopsis, genre_id) VALUES ('Homem aranha', 'Muitas surpresas vão encontrar',2005,'https://cdn.pixabay.com/photo/2020/05/31/09/40/online-course-5242018_1280.jpg','O Homem-Aranha precisa lidar com as consequências da sua verdadeira identidade ter sido descoberta.',2);

INSERT INTO tb_reviews (text, user_id, movie_id) VALUES ('MUITO BOM ',1,1);
INSERT INTO tb_reviews (text, user_id, movie_id) VALUES ('AMEI ',2,1);

