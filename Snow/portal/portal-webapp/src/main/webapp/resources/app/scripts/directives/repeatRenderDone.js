// Generated by CoffeeScript 1.7.1
(function() {
  'use strict';

  /*
   @ngdoc directive
   @name snowApp.directive:renderDone
   @description
    * renderDone
   */
  angular.module('snowApp').directive('repeatRenderDone', function() {
    return {
      restrict: 'A',
      priority: 10,
      link: function(scope, element, attrs) {
        if (scope.$last) {
          console.log('Render Done!');
          return !0;
        }
      }
    };
  });

}).call(this);

//# sourceMappingURL=repeatRenderDone.map
