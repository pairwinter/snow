'use strict';

/**
 * @ngdoc directive
 * @name snowApp.directive:course/examples/map/openlayersDirective
 * @description
 * # course/examples/map/openlayersDirective
 */
angular.module('snowApp')
  .directive('course/examples/map/openlayersDirective', function () {
    return {
      template: '<div></div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        element.text('this is the course/examples/map/openlayersDirective directive');
      }
    };
  });
