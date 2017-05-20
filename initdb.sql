DROP TABLE IF EXISTS emp CASCADE;
CREATE TABLE emp(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    ename VARCHAR,
    hiredate DATE
);
INSERT INTO emp(ename, hiredate) VALUES('scott', '2017-01-02');
INSERT INTO emp(ename, hiredate) VALUES('tiger', '2017-01-12');
