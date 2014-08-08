'use strict';

describe('Controller: CourseExamplesMapGooglemapcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('resourcesApp'));

  var CourseExamplesMapGooglemapcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CourseExamplesMapGooglemapcontrollerCtrl = $controller('CourseExamplesMapGooglemapcontrollerCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
