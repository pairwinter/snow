'use strict';

describe('Controller: CourseExamplesMapOpenlayerscontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('resourcesApp'));

  var CourseExamplesMapOpenlayerscontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CourseExamplesMapOpenlayerscontrollerCtrl = $controller('CourseExamplesMapOpenlayerscontrollerCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
