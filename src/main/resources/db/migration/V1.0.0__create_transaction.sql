create table transaction
(
    id              uuid not null
        constraint transaction_pkey
            primary key,
    created_at      timestamp,
    entity_status   varchar(255),
    updated_at      timestamp,
    description     varchar(255),
    operation       varchar(255),
    source_amount   numeric(19, 2),
    source_currency varchar(5),
    target_amount   numeric(19, 2),
    target_currency varchar(5)
);