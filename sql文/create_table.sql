-- データベースの初期設定
-- テーブルの作成
CREATE TABLE COMPANY(
    id VARCHAR(50) PRIMARY KEY,
    company_name VARCHAR(50) NOT NULL,
    clock_pass VARCHAR(100) NOT NULL,
    admin_pass VARCHAR(100) NOT NULL
);

CREATE TABLE EMPLOYEE(
    id INTEGER NOT NULL,
    company_id VARCHAR(50) NOT NULL,
    employee_name VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    FOREIGN KEY(company_id) REFERENCES COMPANY(id),
    PRIMARY KEY(id,company_id)
);

CREATE TABLE ATTENDANCE(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    company_id VARCHAR(50),
    employee_id INTEGER,
    clock_date DATE,
    event_type VARCHAR(20),
    event_time TIMESTAMP,
    FOREIGN KEY (employee_id, company_id) REFERENCES EMPLOYEE(id, company_id)
);

CREATE TABLE EMPLOYEE_NUM(
    company_id VARCHAR(50) PRIMARY KEY,
    next_employee_id INTEGER,
    FOREIGN KEY(company_id) REFERENCES COMPANY(id)
);