insert into category(id, name) values(1, 'CD');
insert into category(id, name) values(2, 'vinil');
insert into category(id, name) values(3, 'casetă');

insert into participant(id, last_name, first_name) values(1, 'Adam', 'George');

insert into product (id, name, code, reserve_price, restored, seller_id) values (1, 'The Card Players', 'PCEZ', 250, 0, 1);

insert into info(id, product_id, description) values (1, 1, 'Cântece mute');
insert into product_category (product_id, category_id) values(1,1);
insert into participant(id, first_name, last_name) values (2, 'Will', 'Snow');
insert into product (id, name, code, reserve_price, seller_id) values (2, 'Impression, Sunrise', 'PMON', 300, 2);
insert into info(id, product_id, description) values (2, 2, 'Painting');
insert into product_category (product_id, category_id) values (2, 1);
insert into product (id, name, code, reserve_price, seller_id) values (3, 'Balloon Dog' , 'SJEF', 200, 2);
insert into info (id, product_id, description) values (3, 3 , 'Peren');
insert into product_category (product_id, category_id) values (3, 2);