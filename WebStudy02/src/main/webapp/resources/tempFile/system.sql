-----------------------------------------------------------------------------------------------------------------------------
2024.03.27
-----------------------------------------------------------------------------------------------------------------------------
-- 가용 자원 확인 해보기
select *
from V$resource_limit;
-- 커넥션과 클로즈에 상당한 시간이 걸리며,
-- DB 커넥션 자원이 반환되기 전에 다음 커넥션이 이어져서
-- 커넥션 가용 가능한 자원이 줄어들어
-- 아래와 같은 에러가 발생하낟.

--Caused by: oracle.net.ns.NetException: Listener refused the connection with the following error:
--ORA-12516, TNS:listener could not find available handler with matching protocol stack
-----------------------------------------------------------------------------------------------------------------------------

-- 프로세스 갯수 변경, 설정 파일 자체 변경
alter system set PROCESSES = 100 SCOPE=SPFILE;
-----------------------------------------------------------------------------------------------------------------------------
