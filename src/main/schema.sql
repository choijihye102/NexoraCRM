CREATE TABLE member (
    userid VARCHAR(5),
    name VARCHAR(20) NOT NULL,
    img VARCHAR(100),
    email VARCHAR(50) UNIQUE NOT NULL,
    pw VARCHAR(100) NOT NULL,
    role ENUM('MANAGER', 'MEMBER', 'C_LEVEL') NOT NULL DEFAULT 'MEMBER',
    is_admin BOOLEAN NOT NULL DEFAULT FALSE,
    manager_id  VARCHAR(5),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일 (INSERT 시 자동 입력)
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP, -- 수정일

    primary key (userid),
    foreign key (manager_id) references member (userid) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS leads (
    id int auto_increment,                  -- 리드 ID (PK, 자동 증가)
    name VARCHAR(50) ,                                -- 이름
    email VARCHAR(100),                               -- 이메일
    phone VARCHAR(20),                                -- 전화번호
    customer_response VARCHAR(255),                   -- 고객 반응
    company VARCHAR(100),                             -- 회사명
    company_size VARCHAR(50),                         -- 회사 규모
    country VARCHAR(50),                              -- 국가명
    postal_code VARCHAR(20),                          -- 우편번호
    address VARCHAR(255),                             -- 주소
    source VARCHAR(50),                               -- 유입 경로
    status VARCHAR(50),                               -- 상태
    owner_name VARCHAR(18),                           -- 소유자명 (FK: member.userid)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,    -- 생성일 (INSERT 시 자동 입력)
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP, -- 수정일 (UPDATE 시 자동 입력)

    primary key (id)
);

CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- 자동증가 & 기본키 (PK)
    company_name VARCHAR(255) ,  -- 회사명
    country VARCHAR(100) ,       -- 국가
    address VARCHAR(255) ,       -- 주소
    postal_code VARCHAR(20) ,    -- 우편번호
    company_size VARCHAR(50),            -- 회사 규모 (옵션)
    phone VARCHAR(50),                   -- 전화번호
    website VARCHAR(255),                 -- 웹사이트 URL
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 생성일 (자동 입력)
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 업데이트 날짜 (자동 갱신)

    owner_name VARCHAR(100)   -- 담당자 이름
);

CREATE TABLE opptys (
    id INT AUTO_INCREMENT PRIMARY KEY, -- 자동 증가 & 기본 키
    opportunity_name VARCHAR(255),  -- 영업기회명
    amount INT ,  -- 금액 (소수점 2자리까지 지원)
    source VARCHAR(100),  -- 유입 경로
    target_close DATE ,  -- 목표 마감일

    stage VARCHAR(50) DEFAULT '요구분석',  -- 기본값 설정
    notes TEXT,  -- 비고 (긴 글 저장 가능)
    close_date DATE,  -- 실제 마감일

    contact_id INT,  -- contacts 테이블 FK
    account_id INT,  -- accounts 테이블 FK

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,  -- 생성일 (자동 입력)
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정일 (자동 갱신)

    owner_name VARCHAR(100),  -- 담당자 이름

    -- Foreign Key 설정
    CONSTRAINT fk_opptys_contact FOREIGN KEY (contact_id) REFERENCES contacts(id) ON DELETE SET NULL,
    CONSTRAINT fk_opptys_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- 자동 증가 PK
    name VARCHAR(100) ,                 -- 이름
    phone VARCHAR(20),                          -- 전화번호
    email VARCHAR(100),                         -- 이메일
    birthday DATE,                              -- 생년월일 (YYYY-MM-DD)
    department VARCHAR(100),                    -- 부서명
    title VARCHAR(100),                         -- 직책
    account_id INT,  -- accounts 테이블 FK
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일 (자동 입력)
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP, -- 수정일 (자동 업데이트)
    owner_name VARCHAR(100),                   -- 소유자명

    CONSTRAINT fk_contacts_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL

);