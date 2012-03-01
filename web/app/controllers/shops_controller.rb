require 'rexml/document'
class ShopsController < ApiController

  def index
    # Gnavi (http://api.gnavi.co.jp/api/manual.htm)
    case params[:service]
    when "find-shops"
      query = {:keyid => GNAVI_APP_ID, 
               :freeword => params[:keyword],
               :freeword_condition => 1 # AND condition
              }
      # prefecture
      pref_id = params[:prefecture_id].to_i
      if pref_id > 0 then
        pref = Prefecture.find(pref_id)
        query[:pref] = "PREF" << pref.code
      end
      # city
      city_id = params[:city_id].to_i
      if city_id > 0 then
        city = City.find(city_id)
        query[:address] = city.prefecture.name << city.name
      end
      # station
      station_id = params[:station_id].to_i
      if station_id > 0 then
        station = Station.find(station_id)
        query[:freeword] << "," << station.name
      end
      # genre
      # NOTE: only category_l (not support category_s)
      genre_id = params[:genre_id].to_i
      if genre_id > 0 then
        genre = Genre.find(genre_id)
        query[:category_l] = "CTG" << genre.gcode
      end
      
      u = GNAVI_SITE_PAGE << "RestSearchAPI/"
      client = HTTPClient.new
      xml = client.get(u, :query => query).body
      ret = []
      doc = REXML::Document.new(xml)
      doc.root.elements.each("rest") do |r|
        e = r.elements
        a = e["access"].elements
        ret << {:id => e["id"].text, 
                :name => e["name"].text,
                :yomi => e["name_kana"].text,
                :url => e["url"].text,
                :url_mobile => e["url_mobile"].text,
                :address => e["address"].text,
                :tel => e["tel"].text,
                :line => a["line"].text,
                :station => a["station"].text
               }
      end
      render :json => ret
      
    end
  end
end
