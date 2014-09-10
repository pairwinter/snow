'use strict';

###*
 * @ngdoc function
 * @name snowApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the snowApp
###
angular.module('snowApp').controller('MainCtrl', ($scope)->
    $scope.awesomeThings = [
      'HTML5 Boilerplate'
      'AngularJS'
      'Karma'
    ];
  );
