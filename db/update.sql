CREATE TABLE IF NOT EXISTS repository.repository
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (
        INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name text COLLATE pg_catalog."default",
    full_name text COLLATE pg_catalog."default",
    owner_id integer,
    owner_type text COLLATE pg_catalog."default",
    private_repo boolean,
    fork boolean,
    homepage text COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    CONSTRAINT repository_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS repository.commit
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY (
        INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    repo_id bigint,
    sha text COLLATE pg_catalog."default",
    author text COLLATE pg_catalog."default",
    committer text COLLATE pg_catalog."default",
    message text COLLATE pg_catalog."default",
    CONSTRAINT commit_pkey PRIMARY KEY (id),
    CONSTRAINT repo_fkey FOREIGN KEY (repo_id)
        REFERENCES repository.repository (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)