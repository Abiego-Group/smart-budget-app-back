CREATE TABLE IF NOT EXISTS users
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    phone SMALLINT NOT NULL,
    name VARCHAR(64) NOT NULL,
    surname VARCHAR(64),
    middle_name VARCHAR(64),
    age SMALLINT,
    inn VARCHAR(16),
    role VARCHAR,
    wallet_id BIGINT,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp,

    CONSTRAINT fk_wallet_id FOREIGN KEY (wallet_id) REFERENCES wallets (id)
);

CREATE TABLE IF NOT EXISTS wallets
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    balance BIGINT NOT NULL DEFAULT 0,
    transaction_id BIGINT,
    owner_id BIGINT NOT NULL,
    user_id BIGINT,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp,

    CONSTRAINT fk_transaction_history_id FOREIGN KEY (transaction_id) REFERENCES transactions,
    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES users,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE IF NOT EXISTS transactions
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    user_id BIGINT NOT NULL,
    balance_difference BIGINT NOT NULL,
    balance_result BIGINT NOT NULL,
    currency_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp,

    CONSTRAINT fk_currency_id FOREIGN KEY (currency_id) REFERENCES currencies,
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE IF NOT EXISTS currencies
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    name VARCHAR NOT NULL,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS categories
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    name VARCHAR NOT NULL,
    created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp
)