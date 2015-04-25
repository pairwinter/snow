'use strict'

describe 'Service: course/manage/courseservice', ->

  # load the service's module
  beforeEach module 'snowApp'

  # instantiate service
  course/manage/courseservice = {}
  beforeEach inject (_course/manage/courseservice_) ->
    course/manage/courseservice = _course/manage/courseservice_

  it 'should do something', ->
    expect(!!course/manage/courseservice).toBe true
