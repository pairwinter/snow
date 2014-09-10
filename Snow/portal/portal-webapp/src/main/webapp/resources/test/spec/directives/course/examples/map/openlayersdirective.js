'use strict';

describe('Directive: course/examples/map/openlayersDirective', function () {

  // load the directive's module
  beforeEach(module('snowApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<course/examples/map/openlayers-directive></course/examples/map/openlayers-directive>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the course/examples/map/openlayersDirective directive');
  }));
});
