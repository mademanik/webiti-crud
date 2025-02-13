CREATE TABLE public.orders
(
    id          bigserial NOT NULL,
    created_at  timestamp(6) NULL,
    quantity    int4      NOT NULL,
    status      varchar(255) NULL,
    total_price float8    NOT NULL,
    updated_at  timestamp(6) NULL,
    product_id  int8      NOT NULL,
    user_id     int4      NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY ((ARRAY['PENDING':: character varying, 'PROCESSING':: character varying, 'COMPLETED':: character varying, 'CANCELLED':: character varying])::text[])
) )
);


-- public.orders foreign keys

ALTER TABLE public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users (id);
ALTER TABLE public.orders
    ADD CONSTRAINT fkkp5k52qtiygd8jkag4hayd0qg FOREIGN KEY (product_id) REFERENCES public.products (id);