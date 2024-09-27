"use strict";

/**
 * A plugin which adds breadcrumbs to the presentation.
 *
 * @author sedrubal
 */
const RevealBreadcrumb =
  window.RevealBreadcrumb ||
  (function () {
      // var options = window.Reveal.getConfig().breadcrumb || {};

      // create breadcrumb bar
      const reveal = document.getElementsByClassName("reveal")[0]
      const breadcrumb_bar = document.createElement("div");
      breadcrumb_bar.classList.add("breadcrumb");
      reveal.appendChild(breadcrumb_bar);

      // change breadcrumb text on slide change
      window.Reveal.addEventListener("slidechanged", function (event) {
          update_breadcrumb(event.currentSlide);
      });

      // update innerHTML of all .breadcrumb
      function update_breadcrumb(currentSlide) {
          const bs = currentSlide.getElementsByClassName("breadcrumb-data")[0];
          if (bs !== undefined ) {
              breadcrumb_bar.textContent = bs.textContent
          } else {
              breadcrumb_bar.textContent = ''
          }
      }

      update_breadcrumb(window.Reveal.getCurrentSlide());
  })();
