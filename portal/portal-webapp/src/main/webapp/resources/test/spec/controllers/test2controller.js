'use strict';

describe('Controller: Test2controllerCtrl', function () {

  // load the controller's module
  beforeEach(module('snowApp'));

  var Test2controllerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    Test2controllerCtrl = $controller('Test2controllerCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
