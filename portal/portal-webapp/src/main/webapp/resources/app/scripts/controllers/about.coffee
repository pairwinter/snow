'use strict';
###*
 * @ngdoc function
 * @name snowApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the snowApp
###

angular.module('snowApp').controller('AboutCtrl', ($scope)->
    $scope.awesomeThings = [
      'HTML5 Boilerplate'
      'AngularJS'
      'Karma'
    ];
  );
