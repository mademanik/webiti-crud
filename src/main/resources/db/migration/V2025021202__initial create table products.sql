CREATE TABLE public.products
(
    id         bigserial    NOT NULL,
    created_at timestamp(6) NULL,
    "name"     varchar(255) NOT NULL,
    price      float8       NOT NULL,
    updated_at timestamp(6) NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);