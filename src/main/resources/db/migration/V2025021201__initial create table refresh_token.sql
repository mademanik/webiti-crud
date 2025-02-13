CREATE TABLE public.refresh_token
(
    id          serial4 NOT NULL,
    expiry_date int8    NOT NULL,
    "token"     varchar(255) NULL,
    user_id     int4    NOT NULL,
    CONSTRAINT refresh_token_pkey PRIMARY KEY (id),
    CONSTRAINT uk_f95ixxe7pa48ryn1awmh2evt7 UNIQUE (user_id)
);

ALTER TABLE public.refresh_token
    ADD CONSTRAINT fkjtx87i0jvq2svedphegvdwcuy FOREIGN KEY (user_id) REFERENCES public.users (id);