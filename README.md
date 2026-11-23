# Post-Class 10: Observer cho bộ bài Deck

| Thông tin | Nội dung |
|---|---|
| Họ và tên | Huỳnh Ngọc Khang |
| MSSV | 4651050116 |
| Lớp | CNTT46D |
| Bài | Exercise 8.1, 8.2, 8.3 và 8.4 |

Trong bài này, em áp dụng mẫu Observer cho bộ bài `Deck`. Em làm mỗi bài trong một package riêng để dễ chạy và so sánh. Em đặt các lớp `Card`, `Deck` và những kiểu dùng chung trong `src/postclass10/common`.

## Môi trường và cách chạy

- Java 17 trở lên.
- Eclipse IDE for Java Developers.
- Em đã thêm `.project`, `.classpath` và `.settings` để mở project trong Eclipse.

Để chạy bài, em vào **File > Import... > General > Existing Projects into Workspace** trong Eclipse và chọn thư mục `Postclass10`. Sau khi import, em mở lần lượt `Exercise1Demo`, `Exercise2Demo`, `Exercise3Demo`, `Exercise4Demo` rồi chọn **Run As > Java Application**. Em cũng có thể chạy `ObserverChecks` để kiểm tra các thao tác `draw`, `push` và `shuffle`.

```text
Postclass10/
├── README.md
└── src/postclass10/
    ├── common/             # Card, Deck, DeckEvent và observer dùng chung
    ├── exercise1/          # Bài 8.1: push
    ├── exercise2/          # Bài 8.2: pull
    ├── exercise3/          # Bài 8.3: lớp cha hỗ trợ Observer
    ├── exercise4/          # Bài 8.4: lớp con hỗ trợ Observer
    └── ObserverChecks.java
```

## Exercise 8.1: Gửi dữ liệu cho observer (push)

### 1. Yêu cầu của đề

Khi bộ bài thay đổi, `DrawLogger` cần in lá vừa rút và `SizeStatus` cần in số lá còn lại. Bài này yêu cầu dùng cách push, tức là `Deck` gửi thông tin thay đổi cho observer.

### 2. Cách làm

Em tạo [`ObservableDeck`](src/postclass10/exercise1/ObservableDeck.java) để giữ một `Deck` và danh sách observer. Sau mỗi lần `shuffle`, `draw` hoặc `push`, em tạo `DeckEvent` gồm loại thay đổi, lá bài liên quan và số lá hiện tại. Trong hai observer, `DrawLogger` chỉ xử lý sự kiện rút bài, còn `SizeStatus` in số lá ở mọi sự kiện.

Ví dụ khi rút một lá:

```java
Card card = deck.draw();
notifyObservers(new DeckEvent(DeckEvent.Type.DRAWN, card, deck.size()));
```

Observer nhận sẵn lá vừa rút và số lá còn lại, nên không phải hỏi lại `Deck`.

### 3. Chạy ví dụ

Trong `Exercise1Demo`, em đăng ký hai observer, trộn bài, rút hai lá rồi đưa một lá trở lại. Khi chạy lớp này, em nhận được kết quả có dạng:

```text
Số lá hiện tại: 52
Đã rút: <lá bài thứ nhất>
Số lá hiện tại: 51
Đã rút: <lá bài thứ hai>
Số lá hiện tại: 50
Số lá hiện tại: 51
```

Tên lá bài thay đổi giữa các lần chạy vì bộ bài được trộn ngẫu nhiên.

## Exercise 8.2: Observer tự đọc dữ liệu (pull)

### 1. Yêu cầu của đề

Đổi bài 8.1 sang cách pull. Observer được đọc trạng thái của bộ bài nhưng không cần có các phương thức làm thay đổi bộ bài.

### 2. Cách làm

Em tạo [`DeckView`](src/postclass10/exercise2/DeckView.java) chỉ có `size()`, `lastChange()` và `lastDrawn()`. Khi bộ bài thay đổi, em gọi `update(this)` cho các observer. Tham số `update` có kiểu `DeckView`, nên trong mã observer em chỉ dùng được các phương thức để đọc.

```java
public void update(DeckView view) {
    if (view.lastChange() == DeckView.ChangeType.DRAWN) {
        System.out.println("Đã rút: " + view.lastDrawn());
    }
}
```

Lá vừa rút đã ra khỏi bộ bài, nên em lưu nó trong `lastDrawn` để `DrawLogger` đọc được. Với `SizeStatus`, em gọi `view.size()` để lấy số lá. Em không đưa `draw`, `push` hay `shuffle` vào `DeckView`, vì observer chỉ cần đọc trạng thái.

### 3. Chạy ví dụ

Trong `Exercise2Demo`, em làm các bước giống bài 8.1. Khi chạy, số lá được in lần lượt là `52`, `51`, `50`, `51`. Ở bài này, em cho observer lấy thông tin qua `DeckView`.

## Exercise 8.3: Đưa phần hỗ trợ Observer vào lớp cha

### 1. Yêu cầu của đề

Tách phần quản lý observer ra khỏi lớp bộ bài và đưa vào lớp cha. Sau đó nêu ưu điểm, nhược điểm của cách làm này.

### 2. Cách làm

Em tạo [`ObservableSupport<T>`](src/postclass10/exercise3/ObservableSupport.java) để giữ danh sách observer, đăng ký bằng `addObserver` và thông báo bằng `notifyObservers`. Sau đó em cho [`ObservableDeck`](src/postclass10/exercise3/ObservableDeck.java) kế thừa `ObservableSupport<DeckEvent>` và giữ một `Deck` để thực hiện các thao tác với bài.

```java
public class ObservableDeck extends ObservableSupport<DeckEvent> {
    private final Deck deck = new Deck();
}
```

Sau khi rút, thêm hoặc trộn bài, em gọi `notifyObservers` trong `ObservableDeck`.

### 3. Ưu điểm và nhược điểm

- Ưu điểm: Em chỉ viết phần đăng ký và gọi observer một lần trong lớp cha. Nếu có lớp khác cần Observer, em có thể dùng lại `ObservableSupport`.
- Nhược điểm: Java chỉ cho kế thừa một lớp, nên em không thể cho `ObservableDeck` kế thừa thêm `Deck`. Lớp cha cũng không biết lúc nào bộ bài thay đổi; em vẫn phải gọi `notifyObservers` đúng chỗ trong lớp con.

Em chạy `Exercise3Demo` để xem thông báo. Kết quả số lá vẫn là `52`, `51`, `50`, `51`.

## Exercise 8.4: Đưa phần hỗ trợ Observer vào lớp con

### 1. Yêu cầu của đề

Cho `ObservableDeck` kế thừa `Deck`, rồi đặt danh sách observer và phần thông báo trong lớp con. Nêu ưu điểm, nhược điểm của cách làm này.

### 2. Cách làm

Trong [`ObservableDeck`](src/postclass10/exercise4/ObservableDeck.java), em ghi đè `shuffle`, `push` và `draw`. Em gọi `super` để thay đổi bộ bài trước, sau đó mới thông báo cho observer.

```java
@Override
public Card draw() {
    Card card = super.draw();
    notifyObservers(new DeckEvent(DeckEvent.Type.DRAWN, card, size()));
    return card;
}
```

### 3. Ưu điểm và nhược điểm

- Ưu điểm: Em dùng lại mã tạo, trộn và rút bài của `Deck`. Nếu không cần observer, em vẫn dùng `Deck` bình thường.
- Nhược điểm: Nếu `Deck` có thêm phương thức làm thay đổi trạng thái, em phải nhớ ghi đè phương thức đó để gửi thông báo. Em cũng không thể ghi đè phương thức `final`.

Em chạy `Exercise4Demo` để xem kết quả. Trong constructor của `Deck`, em tạo 52 lá bằng phương thức riêng `reset()` để không gọi `shuffle()` của lớp con trước khi danh sách observer được khởi tạo.

## Tóm tắt

| Bài | Cách làm | Observer nhận gì? |
|---|---|---|
| 8.1 | `ObservableDeck` giữ một `Deck` | `DeckEvent` có dữ liệu thay đổi |
| 8.2 | `ObservableDeck` cho đọc qua `DeckView` | `DeckView`, rồi tự đọc dữ liệu |
| 8.3 | `ObservableDeck` kế thừa `ObservableSupport` | `DeckEvent` qua lớp cha |
| 8.4 | `ObservableDeck` kế thừa `Deck` | `DeckEvent` từ lớp con |
