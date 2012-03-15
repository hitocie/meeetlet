class StaticController < ApplicationController
  
  skip_before_filter :authenticate, :only => :index

  def index
    # do nothing
  end

  def main
    render :template => "static/main"
  end
  
  def absentEvent
    render :template => "static/absentEvent"
  end

  def attendEvent
    render :template => "static/attendEvent"
  end
  
  def createArrange
    render :template => "static/createArrange"
  end

  def createInvite
    render :template => "static/createInvite"
  end

  def myEvent
    render :template => "static/myEvent"
  end
  
  def apiTest
    render :template => "static/apiTest"
  end

end
