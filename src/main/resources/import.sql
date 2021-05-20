--Producers
insert into Producer (producerId, name, address, telephone) values (PRODUCER_SEQ.nextval, 'Growers Selection', '94 Guild Street, LONDON, NW9 4TG', '020 8385 6431');
insert into Producer (producerId, name, address, telephone) values (PRODUCER_SEQ.nextval, 'Garden Gang', '87 Simone Weil Avenue, WAY VILLAGE, EX16 8AR', '020 8853 0117');
insert into Producer (producerId, name, address, telephone) values (PRODUCER_SEQ.nextval, 'Farm Stores', '37 Walden Road, GREAT WRATTING, CB9 5TE', '020 8810 5843');
insert into Producer (producerId, name, address, telephone) values (PRODUCER_SEQ.nextval, 'Prime Ripe', '3 Bishopthorpe Road, PEGSWOOD, NE61 8LQ', '020 8766 6359');
insert into Producer (producerId, name, address, telephone) values (PRODUCER_SEQ.nextval, 'Farmers Dream', '25 Sutton Wick Lane, BRIDGERULE, EX22 1PP', '020 8560 0448');

--Groceries
insert into Grocery (groceryId, name, description, producerId, price, quantity) values (GROCERY_SEQ.nextval, 'Apples', 'Super sweet, fresh picked pink apples', 1, 2.0, 10);
insert into Grocery (groceryId, name, description, producerId, price, quantity) values (GROCERY_SEQ.nextval, 'Pears', 'Large freshly picked pears', 1, 3.0, 10);
insert into Grocery (groceryId, name, description, producerId, price, quantity) values (GROCERY_SEQ.nextval, 'Oranges', 'Super falavoursome garden gang selection', 2, 3.0, 10);
insert into Grocery (groceryId, name, description, producerId, price, quantity) values (GROCERY_SEQ.nextval, 'Kiwi', 'Fresh farm kiwi with amazing flavour', 3, 5.0, 10);
insert into Grocery (groceryId, name, description, producerId, price, quantity) values (GROCERY_SEQ.nextval, 'Banana', 'Chosen prime time for prime ripeness maximising flavour', 4, 2.0, 10);

--Baskets
insert into Basket (basketId) values (BASKET_SEQ.nextval);
insert into Basket (basketId) values (BASKET_SEQ.nextval);
insert into Basket (basketId) values (BASKET_SEQ.nextval);

--Add Items To Basket
insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (1, 1);
insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (1, 2);

insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (2, 1);
insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (2, 2);
insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (2, 3);

insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (3, 2);
insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (3, 3);
insert into Basket_Items (Basket_BasketId, Items_GroceryId) values (3, 4);


--Users
insert into Customer (customerId, firstName, lastName, email, password, basketId) values (CUSTOMER_SEQ.nextval, 'Tony', 'Stark', 'stark@hotmail.com', 'password', 1); 
insert into Customer (customerId, firstName, lastName, email, password, basketId) values (CUSTOMER_SEQ.nextval, 'Thor', 'Odin Son', 'thor@hotmail.com', 'thunder', 2); 
insert into Customer (customerId, firstName, lastName, email, password, basketId) values (CUSTOMER_SEQ.nextval, 'Bruce', 'Banner', 'bruce@hotmail.com', 'smash', 3); 












