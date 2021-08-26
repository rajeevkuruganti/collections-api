create table item_images (id int,
     name varchar(33),
     image bytea not null,
      item_id int
    );
create unique index item_id_idx on item_images (item_id);