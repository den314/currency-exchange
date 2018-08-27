CREATE TABLE IF NOT EXISTS CURRENCY (
    id INT PRIMARY KEY,
    code VARCHAR(8) NOT NULL UNIQUE,
    name VARCHAR(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS RATE (
  id INT PRIMARY KEY,
  currency_code INT NOT NULL,
  value DECIMAL (20,5) NOT NULL,
  FOREIGN KEY (currency_code) REFERENCES CURRENCY(id)
);