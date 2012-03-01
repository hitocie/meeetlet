class StaticController < ApplicationController
  
  skip_before_filter :authenticate

  def index
    # do nothing
  end

  def main
    render :template => "static/main"
  end
  
  def foo
    render :template => "static/foo"
  end

  def hoge
    render :template => "static/hoge"
  end
  
end
