alter table collection_Group drop column itemContents;
alter table collection_Group add column itemContents jsonb not null default '{}'::jsonb;