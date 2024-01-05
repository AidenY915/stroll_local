const makeSlider = ($query, minValue, maxValue, digit = 0, unit = "") => {
  $query.find(`#slider-range`).slider({
    range: true,
    min: minValue,
    max: maxValue,
    step: 1,
    values: [minValue, maxValue],
    slide: function (e, ui) {
      var min = Math.floor(ui.values[0]);
      $query.find(".slider-start").html(min / 10 ** digit + unit);
      var max = Math.floor(ui.values[1]);
      $query.find(".slider-end").html(max / 10 ** digit + unit);
    },
  });
};

const sliderHtml = (min, max, digit, unit) => `
<div class="slider">
    <p><span class="slider-start">${
      min / 10 ** digit
    }${unit}</span>에서 <span class="slider-end">${
  max / 10 ** digit
}${unit}까지</span>
    </p>
    <div class="sliders_step1">
        <div class="flat-slider" id="slider-range"></div>
    </div>
</div>
`;

const appendSlider = (parent, min, max, digit, unit) => {
  $(parent).append($(sliderHtml(min, max, digit, unit)));
  makeSlider($(parent), min, max, digit, unit);
};
