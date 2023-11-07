# Board
게시판 만들기 과제

## Use Case Diagram
![image](https://github.com/susik2023/Board/assets/146305978/5a3f7d60-365b-4ab3-9698-596d3e98daea)


|기능|Method|url|request|response|
|------|---|---|----|----|
|게시판조회|GET|/api/boards{id}|{"boardname": boardname,    "writer": writer,    "content": content,    "password":password}|{"boardname": boardname,    "writer": writer,    "content": content,    "password":password}|
|전체게시판조회|GET|/api/boards||{"id":id,"boardname": boardname,    "writer": writer,    "content": content}|
|게시판작성|POST|/api/boards||{"boardname": boardname,    "writer": writer,    "content": content, "password":password}
|게시판수정|PUT|/api/boards/{id}|{"boardname": boardname,    "writer": writer,    "content": content,    "password":password}|{"boardname": boardname,    "writer": writer,    "content": content,    "password":password}
|게시판삭제|DELETE|/api/boards/{id}|{"password":password}|
