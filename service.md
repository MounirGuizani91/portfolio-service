# Principaux types de services Kubernetes (K8s)

## 1\. ClusterIP
- **Définition** : Type par défaut, accessible uniquement à l’intérieur du cluster.
- **Usage** : Communication interne entre pods.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: mon-service-clusterip
spec:
  selector:
    app: mon-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8081
  type: ClusterIP
```

## 2\. NodePort
- **Définition** : Expose le service sur chaque nœud via un port statique.
- **Usage** : Accès externe via NodeIP:NodePort.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: mon-service-nodeport
spec:
  selector:
    app: mon-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8081
      nodePort: 30007
  type: NodePort
```

## 3\. LoadBalancer
- **Définition** : Utilise un load balancer externe pour exposer l’application sur Internet.
- **Usage** : Accès public, généralement en cloud.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: mon-service-loadbalancer
spec:
  selector:
    app: mon-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8081
  type: LoadBalancer
```

## 4\. ExternalName
- **Définition** : Mappe un nom DNS externe à l’intérieur du cluster.
- **Usage** : Rediriger le trafic interne vers un service externe.
```yaml
apiVersion: v1
kind: Service
metadata:
  name: mon-service-externalname
spec:
  type: ExternalName
  externalName: example.com
```


## 4\. Headless Service
- **Définition** : Service sans ClusterIP (spec.clusterIP: "None"), pour obtenir l’IP des pods via DNS.
- **Usage** : Bases de données distribuées, découverte directe des pods.
```yaml
apiVersion: v1
kind: Service
metadata:
  name: mon-service-headless
spec:
  selector:
    app: mon-app
  clusterIP: None
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8081
```

## Résumé des usages

- **ClusterIP** : Communication interne.
- **NodePort** : Ouverture sur un port de chaque nœud.
- **LoadBalancer** : Ouverture via un load balancer externe.
- **ExternalName** : Redirection DNS externe.
- **Headless** : Découverte directe des pods.