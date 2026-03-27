-- change id to primary key
alter table collection_group alter column id set not null ;
alter table collection_group add primary key (id);

-- DROP unused COLUMNS
ALTER TABLE IF EXISTS collection_group DROP COLUMN collectionobj;
ALTER TABLE IF EXISTS collection_group DROP COLUMN artist;
ALTER TABLE IF EXISTS collection_group DROP COLUMN year_released;
ALTER TABLE IF EXISTS collection_group DROP COLUMN edition;
ALTER TABLE IF EXISTS collection_group DROP COLUMN condition_state;
ALTER TABLE IF EXISTS collection_group DROP COLUMN description;
ALTER TABLE IF EXISTS collection_group DROP COLUMN autographed_artist;
ALTER TABLE IF EXISTS collection_group DROP COLUMN country;

-- ALTER Name to have not null.
ALTER TABLE collection_group ALTER COLUMN name SET NOT NULL;
-- create index on name
CREATE INDEX collection_group_name_lower_idx
    ON collection_group ((lower(name)));

-- ADD new columns
ALTER TABLE collection_group ADD created_at timestamptz DEFAULT now();
ALTER TABLE collection_group ADD modified_at timestamptz  DEFAULT now();
ALTER TABLE collection_group ADD deleted_at  timestamptz  DEFAULT now();
ALTER TABLE collection_group ADD user_id int;

