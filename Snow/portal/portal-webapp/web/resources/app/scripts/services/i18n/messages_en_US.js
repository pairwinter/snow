'use strict';

/**
 * @ngdoc service
 * @name snowApp.i18n
 * @description
 * # i18n
 * Factory in the snowApp.
 */
angular.module('snowApp').factory('i18n', function () {
    return {
        "base.home":"Home",
        "base.ie7.lessthen":"<p class=\"browsehappy\">You are using an <strong>outdated</strong> browser. Please <a href=\"http://browsehappy.com/\">upgrade your browser</a> to improve your experience.</p>",
        "nav.course.name":"Course"
    };
});
