class Review {
  #writer;
  #score;
  #content;
  #createdDate;

  static reviews = [];
  static $reviewUl;

  constructor(writer, score, content, createdDate) {
    this.#writer = writer;
    this.#score = score;
    this.#content = content;
    this.#createdDate = createdDate;
  }

  #addReview() {
    let numOfStars = this.#score;
    let stars = "";
    for (let i = 0; i < numOfStars; i++) stars += "★";
    for (let i = numOfStars; i < 5; i++) stars += "☆";
    const $newLi = $(
      `<li class = 'review'>
          <p class = 'writer'>${this.#writer}</p>
          <p class = 'score'>${stars}</p>
          <p class = 'content'>${this.#content}</p>
          <p class = 'createdDate'>${this.#createdDate}</p>
      </li>`
    );
    Review.$reviewUl.append($newLi);
  }
  static addAllReviews() {
    Review.reviews.forEach((e) => {
      e.#addReview();
    });
  }

  static createReview(name, score, content) {
    content = content.trim();
    if (content === "") return;
    const createdDate = new Date();
    const newReview = new Review(
      name,
      score,
      content,
      createdDate.getFullYear() +
        "/" +
        createdDate.getMonth() +
        "/" +
        createdDate.getDate()
    );
    Review.reviews.push(newReview);
    Review.$reviewUl.children().remove();
    Review.addAllReviews();
  }
}

$(() => {
  Review.$reviewUl = $(".reviews");
  Review.addAllReviews();

  $(".commentWritingForm").on("submit", (event) => {
    event.preventDefault();
    const $textarea = $(event.currentTarget).children("textarea");
    const $select = $(event.currentTarget).children("select");
    const content = $textarea.val();
    const score = $select.val();
    Review.createReview("이름", score, content);
    $textarea.val("");
  });

  //임시 댓글 생성
  for (let i = 0; i < 3; i++) {
    Review.createReview("이름", 5, "내용");
  }
});
