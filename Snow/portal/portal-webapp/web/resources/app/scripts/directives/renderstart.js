'use strict';

/**
 * @ngdoc directive
 * @name snowApp.directive:renderStart
 * @description
 * # renderStart
 */
angular.module('snowApp')
    .directive('snowRenderStart', function () {
        return {
            restrict: 'A',
            priority:1,
            link: function postLink(scope, element, attrs) {
                console.log("Render Start!");
            }
        };
    });
