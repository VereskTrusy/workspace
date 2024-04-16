-- 2024. 04. 04


-- LPROD LIST 만들기
-- 상품분류에 따른 거래처(아이디, 거래처명) 정보 가져오기
SELECT
    LPROD.LPROD_ID
    , LPROD.LPROD_GU
    , BUYER.BUYER_ID
    , BUYER.BUYER_NAME
    , BUYER.BUYER_LGU
FROM LPROD
LEFT OUTER JOIN BUYER ON (LPROD.LPROD_GU = BUYER.BUYER_LGU);



--
desc member;
-- 테이블 컬럼 > java 코드로
select 'private '
|| case
    when DATA_TYPE = 'DATE' then 'String '
    when DATA_TYPE = 'NUMBER' then 'Long '
    else 'String '
    end
|| SUBSTR(LOWER(COLUMN_NAME), 1, 1) || SUBSTR(REPLACE(INITCAP(COLUMN_NAME), '_', ''), 2)
|| ';'
from cols
where table_name = 'MEMBER';



















