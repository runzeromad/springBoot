/*테스트 데이터 삽입해줌*/

INSERT INTO article(title, content) VALUES('가가가가','111111');
INSERT INTO article(title, content) VALUES('나나나나','222222');
INSERT INTO article(title, content) VALUES('다다다다','333333');
-- article 테이블에 데이터 추가
INSERT INTO article(title, content) VALUES('당신의 인생영화는?','댓글 고');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?','댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?','댓글 고고고');
-- 4번 게스트 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'park','굿윌헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'kim','아이엠샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'choi','쇼생크탈출');
-- 5번 게스트 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'park','치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'kim','샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'choi','초밥');
-- 6번 게스트 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'park','조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'kim','유트브');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'choi','독서');


INSERT INTO member(email, password) VALUES('test01@test.com','password111');
INSERT INTO member(email, password) VALUES('test02@test.com','password222');
INSERT INTO member(email, password) VALUES('test02@test.com','password333');

INSERT INTO coffee(name, price) VALUES('아메리카노','4500');
INSERT INTO coffee(name, price) VALUES('라떼','5000');
INSERT INTO coffee(name, price) VALUES('카페 모카','5500');

INSERT INTO pizza(name, price) VALUES('페페로니 피자','25900');
INSERT INTO pizza(name, price) VALUES('불고기 피자','29900');
INSERT INTO pizza(name, price) VALUES('고구마 피자','30900');
INSERT INTO pizza(name, price) VALUES('포테이토 피자','27900');
INSERT INTO pizza(name, price) VALUES('치즈 피자','23900');

