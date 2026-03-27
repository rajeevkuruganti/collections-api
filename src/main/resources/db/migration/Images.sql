create table IF NOT EXISTS images (id int PRIMARY KEY,
                     file_givenName varchar(250),
                     minio_stored_fileName varchar(3000),
                     active_flag boolean NOT NULL,
                     created_at timestamptz DEFAULT now() NOT NULL,
                     deleted_at timestamptz DEFAULT now(),
                     created_userId int NOT NULL,
                     collectionGroupId int NOT NULL REFERENCES collection_group(id)
);