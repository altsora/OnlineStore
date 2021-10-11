WITH c_id AS (SELECT id FROM category WHERE name = 'Женщинам')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Одежда'),
       ((SELECT id FROM c_id), 'Большие размеры'),
       ((SELECT id FROM c_id), 'Будущие мамы'),
       ((SELECT id FROM c_id), 'Для высоких'),
       ((SELECT id FROM c_id), 'Для невысоких'),
       ((SELECT id FROM c_id), 'Белье'),
       ((SELECT id FROM c_id), 'Одежда для дома'),
       ((SELECT id FROM c_id), 'Офис'),
       ((SELECT id FROM c_id), 'Пляжная мода'),
       ((SELECT id FROM c_id), 'Свадьба');

WITH c_id AS (SELECT id FROM category WHERE name = 'Мужчинам')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Одежда'),
       ((SELECT id FROM c_id), 'Большие размеры'),
       ((SELECT id FROM c_id), 'Для высоких'),
       ((SELECT id FROM c_id), 'Одежда для дома'),
       ((SELECT id FROM c_id), 'Офис'),
       ((SELECT id FROM c_id), 'Пляжная одежда'),
       ((SELECT id FROM c_id), 'Свадьба');

WITH c_id AS (SELECT id FROM category WHERE name = 'Детям')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Для девочек'),
       ((SELECT id FROM c_id), 'Для мальчиков'),
       ((SELECT id FROM c_id), 'Для новорожденных'),
       ((SELECT id FROM c_id), 'Детская электроника'),
       ((SELECT id FROM c_id), 'Конструкторы'),
       ((SELECT id FROM c_id), 'Детский транспорт'),
       ((SELECT id FROM c_id), 'Детское питание'),
       ((SELECT id FROM c_id), 'Товары для малыша'),
       ((SELECT id FROM c_id), 'Подгузники'),
       ((SELECT id FROM c_id), 'Подарки детям');

WITH c_id AS (SELECT id FROM category WHERE name = 'Обувь')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Детская'),
       ((SELECT id FROM c_id), 'Для новорожденных'),
       ((SELECT id FROM c_id), 'Женская'),
       ((SELECT id FROM c_id), 'Мужская'),
       ((SELECT id FROM c_id), 'Ортопедическая обувь'),
       ((SELECT id FROM c_id), 'Аксессуары для обуви');

WITH c_id AS (SELECT id FROM category WHERE name = 'Аксессуары')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Аксессуары для волос'),
       ((SELECT id FROM c_id), 'Аксессуары для одежды'),
       ((SELECT id FROM c_id), 'Бижутерия'),
       ((SELECT id FROM c_id), 'Галстуки и бабочки'),
       ((SELECT id FROM c_id), 'Зеркальца'),
       ((SELECT id FROM c_id), 'Носовые платки'),
       ((SELECT id FROM c_id), 'Перчатки и варежски'),
       ((SELECT id FROM c_id), 'Платки и шарфы'),
       ((SELECT id FROM c_id), 'Ремни и пояса'),
       ((SELECT id FROM c_id), 'Сумки и рюкзаки');

WITH c_id AS (SELECT id FROM category WHERE name = 'Электроника')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Автоэлектроника и навигация'),
       ((SELECT id FROM c_id), 'Гарнитуры и наушники'),
       ((SELECT id FROM c_id), 'Детская электроника'),
       ((SELECT id FROM c_id), 'Игровые консоли и игры'),
       ((SELECT id FROM c_id), 'Кабели и зарядные устройства'),
       ((SELECT id FROM c_id), 'Музыка и видео'),
       ((SELECT id FROM c_id), 'Ноутбуки и компьютеры'),
       ((SELECT id FROM c_id), 'Офисная техника'),
       ((SELECT id FROM c_id), 'Развлечения и гаджеты'),
       ((SELECT id FROM c_id), 'Сетевое оборудование'),
       ((SELECT id FROM c_id), 'Системы безопасности'),
       ((SELECT id FROM c_id), 'Смартфоны и телефоны'),
       ((SELECT id FROM c_id), 'Смарт-часы и браслеты'),
       ((SELECT id FROM c_id), 'ТВ, аудио, фото и видео техника'),
       ((SELECT id FROM c_id), 'Торговое оборудование'),
       ((SELECT id FROM c_id), 'Умный дом'),
       ((SELECT id FROM c_id), 'Электротранспорт и аксессуары');

WITH c_id AS (SELECT id FROM category WHERE name = 'Книги')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Детям и родителям'),
       ((SELECT id FROM c_id), 'Учебная литература'),
       ((SELECT id FROM c_id), 'Энциклопедии'),
       ((SELECT id FROM c_id), 'Нехудожественная литература'),
       ((SELECT id FROM c_id), 'Художественная литература'),
       ((SELECT id FROM c_id), 'Журналы'),
       ((SELECT id FROM c_id), 'Книги родословные'),
       ((SELECT id FROM c_id), 'Коллекционные издания'),
       ((SELECT id FROM c_id), 'Букинистика'),
       ((SELECT id FROM c_id), 'Блокноты творческие'),
       ((SELECT id FROM c_id), 'Мультимедиа'),
       ((SELECT id FROM c_id), 'Аудиокниги'),
       ((SELECT id FROM c_id), 'Иностранные книги');

WITH c_id AS (SELECT id FROM category WHERE name = 'Спорт')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Фитнес и тренажеры'),
       ((SELECT id FROM c_id), 'Велоспорт'),
       ((SELECT id FROM c_id), 'Йога/Пилатес'),
       ((SELECT id FROM c_id), 'Охота и рыбалка'),
       ((SELECT id FROM c_id), 'Самокаты/Ролики/Скейтборды'),
       ((SELECT id FROM c_id), 'Туризм/Походы'),
       ((SELECT id FROM c_id), 'Бег/Ходьба'),
       ((SELECT id FROM c_id), 'Командные виды спорта'),
       ((SELECT id FROM c_id), 'Водные виды спорта'),
       ((SELECT id FROM c_id), 'Зимние виды спорта'),
       ((SELECT id FROM c_id), 'Поддержка и восстановление'),
       ((SELECT id FROM c_id), 'Единоборства'),
       ((SELECT id FROM c_id), 'Конный спорт'),
       ((SELECT id FROM c_id), 'Мотоспорт'),
       ((SELECT id FROM c_id), 'Парусный спорт'),
       ((SELECT id FROM c_id), 'Страйкбол и пейнтбол'),
       ((SELECT id FROM c_id), 'Для детей'),
       ((SELECT id FROM c_id), 'Для женщин'),
       ((SELECT id FROM c_id), 'Для мужчин'),
       ((SELECT id FROM c_id), 'Спортивная обувь'),
       ((SELECT id FROM c_id), 'Электроника');

WITH c_id AS (SELECT id FROM category WHERE name = 'Школа')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Одежда для девочек'),
       ((SELECT id FROM c_id), 'Одежда для мальчиков'),
       ((SELECT id FROM c_id), 'Банты'),
       ((SELECT id FROM c_id), 'Галстуки и бабочки'),
       ((SELECT id FROM c_id), 'Дошкольные рюкзаки'),
       ((SELECT id FROM c_id), 'Ленты выпускника'),
       ((SELECT id FROM c_id), 'Обувь для девочек'),
       ((SELECT id FROM c_id), 'Обувь для мальчиков'),
       ((SELECT id FROM c_id), 'Ранцы'),
       ((SELECT id FROM c_id), 'Спорт'),
       ((SELECT id FROM c_id), 'Учебная литература'),
       ((SELECT id FROM c_id), 'Школьные принадлежности'),
       ((SELECT id FROM c_id), 'Школьные рюкзаки');

WITH c_id AS (SELECT id FROM category WHERE name = 'Зоотовары')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Для кошек'),
       ((SELECT id FROM c_id), 'Для собак'),
       ((SELECT id FROM c_id), 'Для птиц'),
       ((SELECT id FROM c_id), 'Для грызунов и хорьков'),
       ((SELECT id FROM c_id), 'Для лошадей'),
       ((SELECT id FROM c_id), 'Аквариумистика'),
       ((SELECT id FROM c_id), 'Террариумистика'),
       ((SELECT id FROM c_id), 'Фермерство');

WITH c_id AS (SELECT id FROM category WHERE name = 'Дом')
INSERT
INTO subcategory (category_id, name)
VALUES ((SELECT id FROM c_id), 'Ванная'),
       ((SELECT id FROM c_id), 'Кухня'),
       ((SELECT id FROM c_id), 'Предметы интерьера'),
       ((SELECT id FROM c_id), 'Спальня'),
       ((SELECT id FROM c_id), 'Гостиная'),
       ((SELECT id FROM c_id), 'Детская'),
       ((SELECT id FROM c_id), 'Досуг и творчество'),
       ((SELECT id FROM c_id), 'Зеркала'),
       ((SELECT id FROM c_id), 'Кронштейны'),
       ((SELECT id FROM c_id), 'Освещение'),
       ((SELECT id FROM c_id), 'Для курения'),
       ((SELECT id FROM c_id), 'Отдых на природе'),
       ((SELECT id FROM c_id), 'Мебель'),
       ((SELECT id FROM c_id), 'Прихожая'),
       ((SELECT id FROM c_id), 'Хозяйственные товары'),
       ((SELECT id FROM c_id), 'Хранение вещей'),
       ((SELECT id FROM c_id), 'Шторы');