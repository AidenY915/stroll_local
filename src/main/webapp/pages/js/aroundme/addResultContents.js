const resultList = [];

const liTemplete = (
  imgSrc = "http://placehold.it/180x240",
  placeName = "장소 이름",
  stars = "0"
) => `<a href = 'detail.do'>
  <img class = "placeImg" src=${imgSrc}>
  <div>
  <p class = "placeName">${placeName}</p>
  <p class = "star">${stars}</p>
  </div>
</a>`;

const addLi = function (
  imgSrc = "http://placehold.it/180x240",
  placeName = "장소 이름",
  stars = "0"
) {
  let li = document.createElement("li");
  li.innerHTML = liTemplete(imgSrc, placeName, stars);
  resultList.push(li);
};

for (let i = 0; i < 10; i++) {
  addLi();
}

document.addEventListener("DOMContentLoaded", () => {
  const resultContentsUl = document.querySelector(".results");
  resultList.forEach((e) => {
    resultContentsUl.append(e);
  });
});
