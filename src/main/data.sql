
# memner 생성
insert into member (userid,name,email,pw,role,is_admin,img) values ('10024','시브리','cbeam@nexora.com','1234','C_LEVEL',false,'profileB.png');
insert into member (userid,name,email,pw,role,is_admin,img) values ('10023','제임스','jeim@nexora.com','1234','MANAGER',true,'profileB.png');
insert into member (userid,name,email,pw,role,is_admin,img,manager_id) values ('10022','애드워드','edward@nexora.com','1234','MEMBER',false,'profile.jpg','10023');

commit;

select * from leads;

# lead 생성

INSERT INTO leads (name, email, phone, customer_response, company, company_size, country, postal_code, address, source, status, owner_name)
VALUES
    ('Alice Johnson', 'alice@example.com', '+1-555-1234', 'Interested', 'TechCorp', '100-500', 'USA', '10001', '123 5th Ave, New York, NY', 'Website', '발생', '애드워드');

INSERT INTO leads (name, email, phone, customer_response, company, company_size, country, postal_code, address, source, status, owner_name)
VALUES
    ('Bob Smith', 'bob.smith@example.com', '+44-20-7946-0958', 'Not Interested', 'FinTech Ltd', '500-1000', 'UK', 'SW1A 1AA', '10 Downing Street, London', 'LinkedIn', '발생', '애드워드');

INSERT INTO leads (name, email, phone, customer_response, company, company_size, country, postal_code, address, source, status, owner_name)
VALUES
    ('Charlie Kim', 'charlie.kim@example.com', '+82-2-1234-5678', 'Follow-up Required', 'Korea Tech', '50-200', 'South Korea', '04524', '123 Teheran-ro, Seoul', 'Email Campaign', '발생', '애드워드');

INSERT INTO leads (name, email, phone, customer_response, company, company_size, country, postal_code, address, source, status, owner_name)
VALUES
    ('David Lee', 'david.lee@example.com', '+33-1-2345-6789', 'Meeting Scheduled', 'AI Solutions', '10-50', 'France', '75001', '45 Rue de Rivoli, Paris', 'Referral', '발생', '애드워드');

INSERT INTO leads (name, email, phone, customer_response, company, company_size, country, postal_code, address, source, status, owner_name)
VALUES
    ('Emma Brown', 'emma.brown@example.com', '+49-30-123456', 'Converted', 'GreenTech GmbH', '200-500', 'Germany', '10117', 'Pariser Platz, Berlin', 'Conference', '발생', '애드워드');


# accts 생성
INSERT INTO accounts (company_name, country, address, postal_code, company_size, phone, website, owner_name)
VALUES ('GreenTech GmbH', 'Germany', 'Pariser Platz, Berlin', '10117', '200-500', '+49-30-123456', 'https://greentech.com', '애드워드');


# conts 생성
INSERT INTO contacts (name, phone, email, birthday, department, title, account_id, owner_name)
VALUES
    ('Alice Johnson', '+1-555-1234', 'alice@example.com', '1990-05-10', 'HR', 'Manager', '1', '애드워드'),
    ('Bob Smith', '+44-20-7946-0958', 'bob.smith@example.com', '1985-08-20', 'Finance', 'Analyst', '1', '애드워드'),
    ('Charlie Kim', '+82-10-1234-5678', 'charlie.kim@example.com', '1992-02-15', 'Engineering', 'Software Engineer', '1', '애드워드');

#opptys 생성
INSERT INTO opptys (opportunity_name, amount, source, target_close, stage, notes, close_date, contact_id, account_id, owner_name)
VALUES ('크루아상 프로젝트', 5000000, '박람회', '2025-06-01', '요구분석', '고객과 미팅 완료', NULL, 2, 1, '애드워드');
