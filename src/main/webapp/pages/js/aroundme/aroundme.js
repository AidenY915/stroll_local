let $lastClickedCategory;
let $lastClickedOrderBy;

const clickLi = function () {
  if ($(this).parent().hasClass("categoryUl")) {
    if ($lastClickedCategory !== undefined)
      $lastClickedCategory.css("color", "");
    $lastClickedCategory = $(this);
  } else if ($(this).parent().hasClass("orderByUl")) {
    if ($lastClickedOrderBy !== undefined) $lastClickedOrderBy.css("color", "");
    $lastClickedOrderBy = $(this);
  }
  $(this).css("color", "#ff5f55");
  document.research.category.value = this.innerText;
};

const closeMap = function (e) {
  const map = document.querySelector("#mapContainer");
  if (
    $(map).has(e.target).length === 0 &&
    e.target !== map &&
    e.target !== document.querySelector(".locationBtn")
  ) {
    $(map).hide();
  }
};

$(() => {
  $(".locationBtn").on("click", () => {
    $("#mapContainer").css("display", "block");
    map.relayout();
  });
  $("body").on("click", closeMap);
  $(".categoryUl > li, .orderByUl > li").on("click", clickLi);
  $(".categoryUl > li:first-child, .orderByUl > li:first-child").trigger(
    "click"
  );

  appendSlider(".sliderContainer:eq(0)", 0, 30, 1, "km");
  appendSlider(".sliderContainer:eq(1)", 0, 5, 0, "점");
  appendSlider(".sliderContainer:eq(2)", 0, 30, -2, "개");
});
