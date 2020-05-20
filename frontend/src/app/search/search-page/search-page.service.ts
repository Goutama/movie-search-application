import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Coincidence, LinkLevel, TypeCastInfo} from './search-page.model';

@Injectable({
  providedIn: 'root'
})
export class SearchPageService {

  constructor(private http: HttpClient) {
  }

  getTypecast(searchInput): Observable<HttpResponse<TypeCastInfo>> {
    return this.http.get<TypeCastInfo>(`/api/names/typecast`, {
      params: {
        name: searchInput.sourceName
      },
      observe: 'response'
    });
  }

  getCoincidence(searchInput): Observable<HttpResponse<Coincidence>> {
    return this.http.get<Coincidence>(`/api/names/coincidence`, {
      params: {
        sourceName: searchInput.sourceName,
        targetName: searchInput.targetName
      },
      observe: 'response'
    });
  }

  getLinkLevel(searchInput): Observable<HttpResponse<LinkLevel>> {
    return this.http.get<LinkLevel>(`/api/names/degrees-of-separation`, {
      params: {
        sourceName: searchInput.sourceName,
        targetName: searchInput.targetName
      },
      observe: 'response'
    });
  }
}
