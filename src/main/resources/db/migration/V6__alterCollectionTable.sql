alter table collection_Group
    DROP COLUMN collectionObj,
    ADD COLUMN artist varchar(128),
    ADD COLUMN year_released varchar(4),
    ADD COLUMN edition varchar(64),
    ADD COLUMN condition_state varchar(128),
    ADD COLUMN description varchar(256),
    ADD COLUMN collectible_type varchar(64),
    ADD COLUMN autographed_artist boolean,
    ADD COLUMN country varchar(64)
;