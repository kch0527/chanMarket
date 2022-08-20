# chanMarket

</br>

개발 언어
---------
- Java 11

</br>

개발 환경
---------
- Java: JDK 11.0.12  
- IDE: IntelliJ IDEA 2020.3.3 x64  
- Build Management: Gradle  
- Framework: SpringBoot  
 - ORM: Spring Data JPA   
- DBMS: Mysql


</br>





폴더 구조
---------
```
main
java
com.example.market
│  │  
├─ config
│  └─ Configurations.java
│  └─ RequestListener.java
│  └─ SecurityConfig.java
│  └─ SocketConfig.java
│  └─ WebSocketSessionConfigurator.java
│  │  
├─ controller
│  └─ BasketController.java
│  └─ BoardController.java
│  └─ CommentController.java
│  └─ ExceptionController.java
│  └─ imageController.java
│  └─ LoginController.java
│  └─ LogOutController.java
│  └─ MemberController.java
│  └─ MessageController.java
│  │  
├─ entity
│  └─ basket
│     └─ Basket.java
│     └─ BasketItem.java
│  └─ board
│     └─ Board.java
│     └─ BoardEditor.java
│  └─ comment
│     └─ Comment.java
│     └─ CommentEditor.java
│  └─ member
│     └─ Member.java
│     └─ MemberEditor.java
│     └─ Role.java
│  └─ BaseEntity.java
│  └─ ChatRoom.java
│  └─ Message.java
│  │  
├─ exception
│  └─ BoardNotFound.java
│  └─ CommentNotFound.java
│  └─ MemberNotFound.java
│  └─ TopExcertion.java
│  │  
├─ oauth2
│  └─ CustomOAuth2UserService.java
│  └─ OAuthAttributes.java
│  └─ OAuthController.java
│  │  
├─ repository
│  └─ BaskItemRepository.java
│  └─ ChatRoomRepository.java
│  └─ JpaBasketItemRepository.java
│  └─ JpaBasketRepository.java
│  └─ JpaBoardRepository.java
│  └─ JpaCommentRepository.java
│  └─ JpaMemberRepository.java
│  └─ MessageRepository.java
│  │  
├─ request
│  └─ board
│     └─ BoardCreate.java
│     └─ BoardEdit.java
│  └─ comment
│     └─ CommentCreate.java
│     └─ CommentEdit.java
│  └─ member
│     └─ MemberCreate.java
│     └─ MemberEdit.java
│  │ 
├─ response
│  └─ ErrorResponse.java
│  │ 
├─ service
│  └─ basket
│     └─ BasketService.java
│     └─ BasketServiceImpl.java
│  └─ board
│     └─ BoardService.java
│     └─ BoardServiceImpl.java
│  └─ chatRoom
│     └─ ChatRoomService.java
│     └─ ChatRoomServiceImpl.java
│  └─ comment
│     └─ CommentService.java
│     └─ CommentServiceImpl.java
│  └─ member
│     └─ MemberService.java
│     └─ MemberServiceImpl.java
│  └─ message
│     └─ MessageService.java
│     └─ MessageServiceImpl.java
│  │ 
└─  MarketApplication
```

  
