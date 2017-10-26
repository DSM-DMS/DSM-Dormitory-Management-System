import os

PORT = 81

API_VER = '0.1'
API_TITLE = 'DMS'
API_DESC = '''
[ENDPOINT] http://dsm2015.cafe24.com:{0}

- Status code 중
401 UNAUTHORIZED는 JWT 토큰이 만료되었음을 뜻합니다.
403 Forbidden은 권한이 없음을 뜻합니다.
'''.format(PORT)

SECRET_KEY = os.getenv('SECRET_KEY')
JWT_AUTH_USERNAME_KEY = 'id'
JWT_AUTH_PASSWORD_KEY = 'pw'

MYSQL_PW = os.getenv('MYSQL_PW')
DB_NAME = 'dms'
