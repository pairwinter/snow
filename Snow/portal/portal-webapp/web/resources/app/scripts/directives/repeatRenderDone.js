'use strict';

/**
 * @ngdoc directive
 * @name snowApp.directive:renderDone
 * @description
 * # renderDone
 */
angular.module('snowApp')
    .directive('repeatRenderDone', function () {
        return {
            restrict: 'A',
            priority:10,
            link: function postLink(scope, element, attrs) {
                if (scope.$last) {
                    console.log("Render Done!");
                }
            }
        };
    });
