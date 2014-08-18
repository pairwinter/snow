'use strict';

/**
 * @ngdoc directive
 * @name snowApp.directive:renderDone
 * @description
 * # renderDone
 */
angular.module('snowApp')
    .directive('snowRenderDone', function () {
        return {
            restrict: 'A',
            link: function postLink(scope, element, attrs) {
                if (scope.$last) {
                    console.log("Render Done!");
                }
            }
        };
    });
