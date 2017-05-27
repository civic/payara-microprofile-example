DROP TABLE IF EXISTS dept CASCADE;
DROP TABLE IF EXISTS emp CASCADE;
CREATE TABLE dept(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    dname VARCHAR
);
CREATE TABLE emp(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    ename VARCHAR,
    hiredate DATE,
    deptno INTEGER,
    CONSTRAINT fk_deptno FOREIGN KEY(deptno) REFERENCES dept(id)
);
INSERT INTO dept(id, dname) VALUES(100, 'MANAGER');
INSERT INTO dept(id, dname) VALUES(200, 'SALESMAN');
INSERT INTO dept(id, dname) VALUES(300, 'ENGINEER');

INSERT INTO emp(ename, hiredate, deptno) VALUES('Scott', '2017-01-02', 100);
INSERT INTO emp(ename, hiredate, deptno) VALUES('Tiger', '2017-01-12', 100);
INSERT INTO emp(ename, hiredate, deptno) VALUES('John', '2017-01-11', 200);
INSERT INTO emp(ename, hiredate, deptno) VALUES('Bob', '2017-04-13', 200);
INSERT INTO emp(ename, hiredate, deptno) VALUES('Eli', '2017-04-11', 300);
